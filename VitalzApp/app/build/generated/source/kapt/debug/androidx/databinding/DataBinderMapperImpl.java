package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.technoidentity.vitalz.DataBinderMapperImpl());
  }
}
