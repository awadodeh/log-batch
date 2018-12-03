--
-- Table structure for table `log_item`
--
DROP TABLE IF EXISTS LOG_ITEM;

CREATE TABLE LOG_ITEM (
  `log_date`      datetime     NOT NULL,
  `log_ip`        varchar(55)  NOT NULL,
  `log_request`   varchar(45)  NOT NULL,
  `log_status`    int(11)      NOT NULL,
  `log_user_agent` varchar(255) NOT NULL
);

--
-- Table structure for table `blocked_log_item`
--
DROP TABLE IF EXISTS BLOCKED_LOG_ITEM;

CREATE TABLE BLOCKED_LOG_ITEM (
  `log_ip` varchar(55) NOT NULL,
  `log_requests` int(11) NOT NULL,
  `log_message` varchar(255) NOT NULL
);