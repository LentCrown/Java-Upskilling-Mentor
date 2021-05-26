package org.mentor.patterns.creational.prototype;

public class Wolf extends Animal implements Copyable {
    int tail_size;

    public Wolf(String familyName, int tail_size) {
        super(familyName);
        this.tail_size = tail_size;
    }

    public Wolf(Wolf toClone) {
        super(toClone);
        if (toClone != null)
            this.tail_size = toClone.tail_size;
    }

    @Override
    public Wolf clone() {
        return (Wolf) new Wolf(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        org.mentor.patterns.creational.prototype.Wolf wolf = (org.mentor.patterns.creational.prototype.Wolf) o;
        return tail_size == wolf.tail_size;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tail_size;
        return result;
    }
}
