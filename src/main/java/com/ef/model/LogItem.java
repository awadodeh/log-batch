package com.ef.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table( name = "log_item" )
public class LogItem {

   @Id
   @GeneratedValue( strategy = GenerationType.AUTO )
   private Long id;

   @Column( name = "log_date" )
   private Date date;

   @Column( name = "log_ip" )
   private String IP;

   @Column( name = "log_request" )
   private String request;

   @Column( name = "log_status" )
   private Integer status;

   @Column( name = "log_user_agent" )
   private String userAgent;

   public LogItem() {
   }

   public LogItem( Date date, String IP, String request, Integer status, String userAgent ) {
      this.date = date;
      this.IP = IP;
      this.request = request;
      this.status = status;
      this.userAgent = userAgent;
   }

   public Long getId() {
      return id;
   }

   public void setId( Long id ) {
      this.id = id;
   }

   public Date getDate() {
      return date;
   }

   public void setDate( Date date ) {
      this.date = date;
   }

   public String getIP() {
      return IP;
   }

   public void setIP( String iP ) {
      IP = iP;
   }

   public String getRequest() {
      return request;
   }

   public void setRequest( String request ) {
      this.request = request;
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus( Integer status ) {
      this.status = status;
   }

   public String getUserAgent() {
      return userAgent;
   }

   public void setUserAgent( String userAgent ) {
      this.userAgent = userAgent;
   }

   @Override
   public String toString() {
      return "LogAccess [date=" + date + ", IP=" + IP + ", request=" + request + ", status=" + status + ", userAgent=" + userAgent
            + "]";
   }

   @Override
   public boolean equals( Object o ) {
      if ( this == o )
         return true;
      if ( !(o instanceof LogItem) )
         return false;
      LogItem logItem = (LogItem) o;
      return Objects.equals( getId(), logItem.getId() ) && Objects.equals( getDate(), logItem.getDate() ) && Objects.equals(
            getIP(), logItem.getIP() ) && Objects.equals( getRequest(), logItem.getRequest() ) && Objects.equals( getStatus(),
            logItem.getStatus() ) && Objects.equals( getUserAgent(), logItem.getUserAgent() );
   }

   @Override
   public int hashCode() {
      return Objects.hash( getId(), getDate(), getIP(), getRequest(), getStatus(), getUserAgent() );
   }
}