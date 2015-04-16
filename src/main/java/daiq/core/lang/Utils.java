package daiq.core.lang;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class Utils {



    public static String digest(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(str.getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    
    public static List<Class<?>> listClasses(List<String> packages) {
        return packages.stream()
                .map(Utils::listClasses)
                .flatMap(l -> l.stream())
                .collect(Collectors.toList());
    }

    
    public static List<Class<?>> listClasses(String packageName) {
        
        final String resourceName = packageName.replace('.', '/');
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final URL root = classLoader.getResource(resourceName);
        
        if ("file".equals(root.getProtocol())) {
            File[] files = new File(root.getFile()).listFiles((dir, name) -> name.endsWith(".class"));
            return Arrays.asList(files).stream()
                    .map(file -> file.getName())
                    .map(name -> name.replaceAll(".class$", ""))
                    .map(name -> packageName + "." + name)
                    .map(fullName -> Langs.uncheck(() -> Class.forName(fullName)))
                    .collect(Collectors.toList());
        }
        if ("jar".equals(root.getProtocol())) {
            try (JarFile jarFile = ((JarURLConnection) root.openConnection()).getJarFile()) {
                return Collections.list(jarFile.entries()).stream()
                        .map(jarEntry -> jarEntry.getName())
                        .filter(name -> name.startsWith(resourceName))
                        .filter(name -> name.endsWith(".class"))
                        .map(name -> name.replace('/', '.').replaceAll(".class$", ""))
                        .map(fullName -> Langs.uncheck(() -> classLoader.loadClass(fullName)))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new ArrayList<>();
    }
    
}