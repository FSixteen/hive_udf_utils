package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class DealDate extends GenericUDF {

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    try {
      if (null == arguments || null == arguments[0] || null == arguments[0].get()) return null;
      String date = arguments[0].get().toString();
      if (19 == date.trim().length()) return date.trim();
      else if (16 == date.trim().length()) return date.trim() + ":00";
      else if (10 == date.trim().length()) return date.trim() + " 00:00:00";
      else if (8 == date.trim().length())
        return date.trim().substring(0, 4) + "-" + date.trim().substring(4, 6) + "-" + date.trim().substring(6, 8) + " 00:00:00";
      else return date;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "时间转换(yyyy-MM-dd HH:mm:ss)!";
  }

}
