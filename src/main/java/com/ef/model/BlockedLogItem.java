package com.ef.model;

import javax.persistence.*;

@Entity
@Table( name = "blocked_log_item" )
public class BlockedLogItem {

   @Id
   @GeneratedValue( strategy = GenerationType.AUTO )
   private Long id;

   @Column( name = "log_ip" )
   private String IP;

   @Column( name = "log_request" )
   private int request;

   @Column( name = "log_message" )
   private String message;

   public BlockedLogItem() {
   }

   public BlockedLogItem( String IP, int request, String message ) {
      this.IP = IP;
      this.request = request;
      this.message = message;
   }

   public Long getId() {
      return id;
   }

   public void setId( Long id ) {
      this.id = id;
   }

   public String getIP() {
      return IP;
   }

   public void setIP( String iP ) {
      IP = iP;
   }

   public int getRequest() {
      return request;
   }

   public void setRequest( int request ) {
      this.request = request;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage( String message ) {
      this.message = message;
   }

   @Override
   public String toString() {
      return "IP: " + IP + " | request: " + request + " | message: " + message;
   }

}