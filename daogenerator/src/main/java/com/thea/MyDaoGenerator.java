package com.thea;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {
    private static final String JAVA_PACKAGE_NAME = "com.thea.base.bean";
    private static final String JAVA_PACKAGE_DAO = "com.thea.base.dao";
    private static final String CODE_OUT_DIRECTORY = "D:\\Document\\Workspace\\AS\\Base\\" +
            "app\\src\\main\\java-gen";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, JAVA_PACKAGE_NAME);
        schema.setDefaultJavaPackageDao(JAVA_PACKAGE_DAO);
        
        addTableUser(schema);
        new DaoGenerator().generateAll(schema, CODE_OUT_DIRECTORY);
    }

    private static void addTableUser(Schema schema) {
        Entity table = schema.addEntity("User");
        // 表名默认与类名一致，也可重新设置
//        table.setTableName("user");

        table.addIdProperty();
        table.addStringProperty("userName").unique();
        table.addStringProperty("phone");
        table.addStringProperty("password").notNull();
    }
}
