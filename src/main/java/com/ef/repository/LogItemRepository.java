package com.ef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import com.ef.model.LogItem;

/**
 * This is a repository class for LogItem
 *
 * @Auther: awad
 */
@Repository
public interface LogItemRepository extends JpaRepository<LogItem, Long> {

   /**
    * This method is to find IPs that made more than a certain number of requests for a given time period
    * @param startDate
    * @param endDate
    * @param threshold
    * @return
    */
   @Query( value = "select DISTINCT log_ip "
         + "from log_item where log_date BETWEEN ? and ? group by log_ip having count(log_ip) >= ?", nativeQuery = true )
   Collection<String> findByDateRangeAndThreshold( Date startDate, Date endDate, Integer threshold );

   /**
    * query to find request made by a given IP.
    * @param ip
    * @return
    */
   @Query( value = "select id,log_date, log_ip, log_request, log_status, log_user_agent from log_item where log_ip = ?", nativeQuery = true )
   Collection<LogItem> findLogItemByIP( String ip );
}
