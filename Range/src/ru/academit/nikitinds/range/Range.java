package ru.academit.nikitinds.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return from <= number && number <= to;
    }

    public Range getIntersection(Range range) {
        if (range.from >= this.to || this.from >= range.to) {
            return null;
        }

        double maxFrom = Math.max(this.from, range.from);
        double minTo = Math.min(this.to, range.to);
        return new Range(maxFrom, minTo);
    }

    public Range[] getUnion(Range range) {
        double minFrom = Math.min(this.from, range.from);
        double maxFrom = Math.max(this.from, range.from);
        double minTo = Math.min(this.to, range.to);
        double maxTo = Math.max(this.to, range.to);

        if (range.from > this.to || this.from > range.to) {
            return new Range[]{new Range(minFrom, minTo), new Range(maxFrom, maxTo)};
        }

        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getComplement(Range range) {
        if (range.from >= this.to || this.from >= range.to) {
            return new Range[]{new Range(this.from, this.to)};
        }

        if (this.from < range.from) {
            if (this.to > range.to) {
                return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
            }

            return new Range[]{new Range(this.from, range.from)};
        }

        if (this.to > range.to) {
            return new Range[]{new Range(range.to, this.to)};
        }

        return new Range[]{};
    }

    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}