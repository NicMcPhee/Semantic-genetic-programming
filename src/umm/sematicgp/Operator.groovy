package umm.sematicgp;

/**
 * A simple Groovy class that wraps a function that isn't
 * specified here, but is presumably specified when the instance
 * of Operator is constructed (or in a subclass of Operator).
 * We wouldn't actually do this in the GP system because we
 * could/would just pass the function around instead of wrapping
 * it like this, but I thought I'd show some of the stuff you can
 * do.
 */
class Operator {
    def apply(x, y) {
        func(x, y)
    }
}