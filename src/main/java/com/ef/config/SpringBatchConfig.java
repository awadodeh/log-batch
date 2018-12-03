package com.ef.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.ef.model.LogItem;

import static com.ef.ParserConstants.*;

/**
 * This class contains the configuration for batch job
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

   private static final Logger log = LoggerFactory.getLogger( SpringBatchConfig.class );

   @Autowired
   private JobBuilderFactory jobBuilderFactory;

   @Autowired
   private StepBuilderFactory stepBuilderFactory;

   @Autowired
   private ItemReader<LogItem> itemReader;

   @Autowired
   @Qualifier( "logItemProcessor" )
   private ItemProcessor<LogItem, LogItem> itemProcessor;

   @Autowired
   @Qualifier( "DatabaseWriter" )
   private ItemWriter<LogItem> itemWriter;

   @Bean
   public Job job() {

      Step step = stepBuilderFactory.get( LOAD_LOG_FILE ).<LogItem, LogItem>chunk( 100 ).reader( itemReader )
                                                                                        .processor( itemProcessor )
                                                                                        .writer( itemWriter )
                                                                                        .build();


      return jobBuilderFactory.get( LOAD_LOG ).incrementer( new RunIdIncrementer() ).start( step ).build();
   }

   @Bean
   public FlatFileItemReader<LogItem> itemReader( @Value( "${input}" ) Resource resource ) {

      FlatFileItemReader<LogItem> flatFileItemReader = new FlatFileItemReader<>();
      flatFileItemReader.setResource( resource == null ? new FileSystemResource( LOG_FILE_NAME ) : resource );
      flatFileItemReader.setName( ACCESS_LOG_READER );
      //        in case there was a header
      //        flatFileItemReader.setLinesToSkip(1);
      flatFileItemReader.setLineMapper( lineMapper() );
      return flatFileItemReader;
   }

   @Bean
   public LineMapper<LogItem> lineMapper() {
      DefaultLineMapper<LogItem> defaultLineMapper = new DefaultLineMapper<>();
      DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

      lineTokenizer.setDelimiter( DELIMITER );
      lineTokenizer.setStrict( true );
      lineTokenizer.setNames( FILE_FIELDS );

      BeanWrapperFieldSetMapper<LogItem> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
      fieldSetMapper.setTargetType( LogItem.class );

      final DateFormat df = new SimpleDateFormat( DATE_FORMAT );
      final Map<Class, PropertyEditor> customEditors = Stream.of(
            new AbstractMap.SimpleEntry<>( Date.class, new CustomDateEditor( df, true ) ) )
            .collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue ) );

      fieldSetMapper.setCustomEditors( customEditors );

      defaultLineMapper.setLineTokenizer( lineTokenizer );
      defaultLineMapper.setFieldSetMapper( fieldSetMapper );

      return defaultLineMapper;
   }

}
