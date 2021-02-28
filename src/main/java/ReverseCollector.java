import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ReverseCollector<A, T extends Collection<A>> implements Collector<A, List<A>, Collection<A>> {

    private final IBuilder<T> builder;

    public ReverseCollector(IBuilder<T> builder){
        this.builder = builder;
    }

    @Override
    public Supplier<List<A>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<A>, A> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<A>> combiner() {
        return (l, r) -> { l.addAll(r); return l; };
    }

    @Override
    public Function<List<A>, Collection<A>> finisher() {
        return r -> {
            Collection<A> result = builder.build();
            for (int i = r.size() - 1; i >= 0; i--) {
                result.add(r.get(i));
            }
            return result;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
