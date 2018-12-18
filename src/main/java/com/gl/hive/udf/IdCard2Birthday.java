package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class IdCard2Birthday extends GenericUDF {

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    if (null == arguments || null == arguments[0] || null == arguments[0].get()) return null;
    String id = arguments[0].get().toString();
    StringBuilder result = null;
    if (15 == id.length()) result = new StringBuilder(20).append("19").append(id.substring(6, 8)).append("-").append(id.substring(8, 10))
        .append("-").append(id.substring(10, 12)).append(" 00:00:00");
    else if (18 == id.length()) result = new StringBuilder(20).append(id.substring(6, 10)).append("-").append(id.substring(10, 12))
        .append("-").append(id.substring(12, 14)).append(" 00:00:00");
    else result = null;
    return null == result ? null : result.toString();
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "18位身份证号码提取出生日期(yyyy-MM-dd HH:mm:ss)!";
  }

}
