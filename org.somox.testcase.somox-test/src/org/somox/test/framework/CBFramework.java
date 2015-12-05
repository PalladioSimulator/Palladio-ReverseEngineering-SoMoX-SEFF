package org.somox.test.framework;

public class CBFramework {

    public static <T> T instanciate(final String classname, final Class<T> clazz) {
        try {
            return (T) Class.forName(classname).newInstance();
        } catch (final InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
