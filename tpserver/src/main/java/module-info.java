open module com.fhtw.tpserver {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires jakarta.persistence;
    requires static lombok;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires org.hibernate.orm.core;
    requires spring.context;
    requires com.fasterxml.jackson.annotation;
    requires jakarta.annotation;
    requires org.apache.logging.log4j;

    exports com.fhtw.tpserver;
    exports com.fhtw.tpserver.rest;
    exports com.fhtw.tpserver.model;
}