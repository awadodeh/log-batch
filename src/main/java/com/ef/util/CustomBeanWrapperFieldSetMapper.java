package com.ef.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.util.StringUtils;
import org.springframework.validation.DataBinder;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ef.ParserConstants.DEFAULT_DATE_FORMAT;

public class CustomBeanWrapperFieldSetMapper<T> extends BeanWrapperFieldSetMapper<T> {

   private static final Logger log = LoggerFactory.getLogger( CustomBeanWrapperFieldSetMapper.class );

   @Override
   protected void initBinder( DataBinder binder ) {
      log.debug( "CustomBeanWrapperFieldSetMapper.initBinder" );
      DateFormat format = new SimpleDateFormat( DEFAULT_DATE_FORMAT );
      binder.registerCustomEditor( Date.class, new PropertyEditorSupport() {

         @Override
         public String getAsText() throws IllegalArgumentException {
            Object date = getValue();
            if ( date != null ) {
               return format.format( (Date) getValue() );
            } else {
               return "";
            }
         }

         @Override
         public void setAsText( String text ) throws IllegalArgumentException {
            if ( !StringUtils.isEmpty( text ) ) {
               try {
                  setValue( format.parse( text ) );
               }
               catch ( ParseException e ) {
                  log.error( e.getMessage() );
                  e.printStackTrace();
               }
            } else {
               setValue( null );
            }
         }
      } );
   }
}
