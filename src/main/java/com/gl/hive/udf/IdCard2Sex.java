package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class IdCard2Sex extends GenericUDF {

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    return PrimitiveObjectInspectorFactory.javaShortObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    if (null == arguments || null == arguments[0] || null == arguments[0].get()) return null;
    String idcard = arguments[0].get().toString();
    Short id = null;
    try {
      if (15 == idcard.length()) id = Short.valueOf(arguments[0].get().toString().substring(14, 15));
      else if (18 == idcard.length()) id = Short.valueOf(arguments[0].get().toString().substring(16, 17));
      else id = null;
    } catch (Exception e) {
      id = null;
    }
    if (null == id) return null; // null: 无法判断
    else if (id % 2 == 0) return (short) 2; // 2: 女
    else return (short) 1; // 1: 男
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "18位身份证号码提取性别(1:男, 2:女)!";
  }

}
