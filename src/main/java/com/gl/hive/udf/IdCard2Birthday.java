package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

public class IdCard2Birthday extends GenericUDF {

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    if (arguments.length != 1) throw new UDFArgumentLengthException("仅支持一个参数: String arg");
    if (!(arguments[0] instanceof StringObjectInspector)) throw new UDFArgumentLengthException("仅支持一个参数: String arg");
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    String id = arguments[0].get().toString();
    StringBuilder result = new StringBuilder(id.substring(6, 10)).append("-").append(id.substring(10, 12)).append("-")
        .append(id.substring(12, 14)).append(" 00:00:00");
    return result.toString();
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "18位身份证号码提取出生日期(yyyy-MM-dd HH:mm:ss)!";
  }

}
