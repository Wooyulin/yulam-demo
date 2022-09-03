package sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Sample1_HelloWorld {

    @Benchmark
    public void measureHelloWorld(Blackhole blackhole) {

    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Sample1_HelloWorld.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
