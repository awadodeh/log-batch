package com.ef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import com.ef.model.BlockedLogItem;

import static com.ef.ParserConstants.BLOCK_MESSAGE;

@Repository
public interface BlockedLogItemRepository extends JpaRepository<BlockedLogItem, Long> {

   @Query( value = "INSERT INTO blocked_log_item (log_ip, log_requests, log_message) "
         + "select DISTINCT log_ip, COUNT(log_ip) as log_requests, " + BLOCK_MESSAGE +" as log_message "
         + "from log_item where log_date BETWEEN ? and ? group by log_ip having count(log_ip) >= ?", nativeQuery = true )
   void saveBlockedLogItemBetweenDateAndThreshold( LocalDateTime startDate, LocalDateTime endDate, Integer threshold );
}
