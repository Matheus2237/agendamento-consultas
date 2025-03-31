package br.com.matheus.agendamentoconsultas.util;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

@NoArgsConstructor
public class ReflectionUtil {

    @SneakyThrows
    public static void setPrivateId(Object entity, Long id) {
        Field field = entity.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(entity, id);
    }
}
