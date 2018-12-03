package com.ef.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.ef.model.LogItem;

@Component("logItemProcessor")
public class LogItemProcessor implements ItemProcessor<LogItem, LogItem> {

    private static final Logger log = LoggerFactory.getLogger( LogItemProcessor.class);

    @Override
    public LogItem process( LogItem logItem ) throws Exception {
        log.debug( "Processing: " + logItem.toString() );
        return logItem;
    }
}
