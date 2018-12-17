package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;

public class IdCard2Sex extends GenericUDF {

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    if (arguments.length != 1) throw new UDFArgumentLengthException("仅支持一个参数: String arg");
    if (!(arguments[0] instanceof StringObjectInspector)) throw new UDFArgumentLengthException("仅支持一个参数: String arg");
    return PrimitiveObjectInspectorFactory.javaShortObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    Short id = Short.valueOf(arguments[0].get().toString().substring(16, 17));
    if (id % 2 == 0) return 2;
    else return 1;
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "18位身份证号码提取性别(1:男, 2:女)!";
  }

}
