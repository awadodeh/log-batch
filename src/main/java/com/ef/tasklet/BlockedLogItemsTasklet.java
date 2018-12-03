package com.ef.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.sql.DataSource;
import com.ef.notification.JobCompletionNotificationListener;
import com.ef.repository.BlockedLogItemRepository;

import static com.ef.ParserConstants.DEFAULT_DATE_FORMAT;

@Repository
public class BlockedLogItemsTasklet implements Tasklet {

   private static final Logger log = LoggerFactory.getLogger( JobCompletionNotificationListener.class );

   private final DataSource dataSource;

   @Autowired
   private BlockedLogItemRepository blockedLogItemRepository;

   public BlockedLogItemsTasklet( DataSource dataSource ) {
      this.dataSource = dataSource;
   }

   @Override
   public RepeatStatus execute( StepContribution stepContribution, ChunkContext chunkContext ) {

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern( DEFAULT_DATE_FORMAT );
      LocalDateTime startDate = LocalDateTime.parse( "2017-01-01 00:00:11.763", formatter );
      LocalDateTime endDate = LocalDateTime.parse( "2017-01-02 00:00:11.763", formatter );
      Integer threshold = 200;
      blockedLogItemRepository.saveBlockedLogItemBetweenDateAndThreshold( startDate, endDate, threshold );

      return RepeatStatus.FINISHED;
   }
}
