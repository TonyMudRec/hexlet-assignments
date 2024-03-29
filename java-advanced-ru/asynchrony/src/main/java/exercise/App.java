package exercise;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String source1, String source2, String export) {
        CompletableFuture<String> source1Data = getFuture(source1);
        CompletableFuture<String> source2Data = getFuture(source2);

        return source1Data.thenCombine(source2Data, (source1String, source2String) -> {
            Path exportPath = Path.of(export).toAbsolutePath().normalize();
            try {
                if (Files.exists(exportPath)) {
                    Files.write(exportPath, (source1String + source2String).getBytes());
                } else {
                    Files.createFile(exportPath);
                    Files.write(exportPath, (source1String + source2String).getBytes());
                }
            } catch (IOException ex) {
                ex.getMessage();
            }
            return source1String + source2String;
        });
    }

    private static CompletableFuture<String> getFuture(String source) {
        return CompletableFuture.supplyAsync(() -> {
            Path path = Path.of(source).toAbsolutePath().normalize();
            try {
                return Files.readString(path);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        }).exceptionally(err -> {
            System.out.println("Oops! We have an exception - " + err.getMessage());
            return null;
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String dir) {
        return CompletableFuture.supplyAsync(() -> {
            Path path = Path.of(dir).toAbsolutePath().normalize();
            long sum = 0L;
            try {
                var list = Files.list(path).filter(Files::isRegularFile).toList();
                for (Path path1 : list) {
                    sum += Files.size(path1);
                }
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
            return sum;
        });
    }

    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles("file1.txt", "file2.txt", "dest.txt");
        result.get();
        // END
    }
}

