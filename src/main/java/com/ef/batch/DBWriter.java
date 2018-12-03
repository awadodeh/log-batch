package com.ef.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import com.ef.model.LogItem;
import com.ef.repository.LogItemRepository;

@Component
@Qualifier(value = "DatabaseWriter")
public class DBWriter implements ItemWriter<LogItem> {

    @Autowired
    private LogItemRepository logItemRepository;

    @Override
    public void write(List<? extends LogItem> logItems) throws Exception {

        System.out.println("Data Saved for LogItems: " + logItems);
        logItemRepository.saveAll(logItems);
    }
}
