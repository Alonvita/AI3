/**
 * Distance<T> interface.
 */
public interface Distance<T> {
    /**
     *
     * @param src T -- source to calculate from
     * @param dst T -- destination to calculate to
     *
     * @return the distanceFrom between src to dst
     */
    Double calcDistance(T src, T dst);
}
