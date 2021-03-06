package com.gl.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

public class SBC2DBC extends GenericUDF {

  @Override
  public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {
    return PrimitiveObjectInspectorFactory.javaStringObjectInspector;
  }

  @Override
  public Object evaluate(DeferredObject[] arguments) throws HiveException {
    if (null == arguments || null == arguments[0] || null == arguments[0].get()) return null;
    String id = arguments[0].get().toString();
    StringBuilder result = new StringBuilder(id.length());
    for (int i = 0; i < id.length(); i++) {
      char c = id.charAt(i);
      if (65281 <= c && c <= 65374) result.append((char) (c - 65248));
      else result.append(c);
    }
    return result.toString();
  }

  @Override
  public String getDisplayString(String[] children) { // 显示函数的帮助信息
    return "全角转半角!";
  }

}
