package com.uberApplication.uber.exception;

public class RuntimeConflictException extends RuntimeException{
   public RuntimeConflictException() {
   }

   public RuntimeConflictException(String message) {
       super(message);
   }
}
