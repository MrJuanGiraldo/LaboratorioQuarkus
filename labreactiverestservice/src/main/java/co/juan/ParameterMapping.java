package co.juan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.reactive.RestQuery;

@Provider
class MyConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType,
                                              Annotation[] annotations) {
        // declare a converter for this type
        if(rawType == Converter.class) {
            return (ParamConverter<T>) new MyConverter();
        }
        return null;
    }

}

// this is my custom converter
class MyConverter implements ParamConverter<Converter> {

    @Override
    public Converter fromString(String value) {
        return new Converter(value);
    }

    @Override
    public String toString(Converter value) {
        return value.value;
    }

}

// this uses a converter
class Converter {
    String value;
    Converter(String value) {
        this.value = value;
    }
}

class Constructor {
    String value;
    // this will use the constructor
    public Constructor(String value) {
        this.value = value;
    }
}

class ValueOf {
    String value;
    private ValueOf(String value) {
        this.value = value;
    }
    // this will use the valueOf method
    public static ValueOf valueOf(String value) {
        return new ValueOf(value);
    }
}

@Path("parameter")
public class ParameterMapping {

    @Path("/{converter}/{constructor}/{primitive}/{valueOf}")
    @GET
    public String conversions(Converter converter, Constructor constructor,
                              int primitive, ValueOf valueOf,
                              @RestQuery List<Constructor> list) {
        return converter + "/" + constructor + "/" + primitive
                + "/" + valueOf + "/" + list;
    }
}