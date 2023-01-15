import java.util.List;

public interface IMetier<T, U> {
    public T add(T o);
    public List<T> getAll();
    public T findById(U id);
    public void delete(U id);
    public void saveAll();
}