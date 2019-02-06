package com.nsromapa.sqlitecipher;

public final class ProductContacts {


    public ProductContacts() {
    }

    public static abstract class ProductEntry{

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String QTY = "qty";
        public static final String TABLE_NAME = "product_table";

        public static final String CREATE_QUERY = "create table "+TABLE_NAME+" ("+ID+" TEXT,"+NAME+" TEXT,"+PRICE+" INTEGER,"+QTY+" INTEGER);";

    }

}
