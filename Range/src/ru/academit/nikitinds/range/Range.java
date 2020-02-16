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
        if (range.from >= to || from >= range.to) {
            return null;
        }

        double maxFrom = Math.max(from, range.from);
        double minTo = Math.min(to, range.to);
        return new Range(maxFrom, minTo);
    }

    public Range[] getUnion(Range range) {
        double minFrom = Math.min(from, range.from);
        double maxFrom = Math.max(from, range.from);
        double minTo = Math.min(to, range.to);
        double maxTo = Math.max(to, range.to);

        if (range.from > to || from > range.to) {
            return new Range[]{new Range(minFrom, minTo), new Range(maxFrom, maxTo)};
        }

        return new Range[]{new Range(minFrom, maxTo)};
    }

    public Range[] getComplement(Range range) {
        if (range.from >= to || from >= range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from < range.from) {
            if (to > range.to) {
                return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            }

            return new Range[]{new Range(from, range.from)};
        }

        if (to > range.to) {
            return new Range[]{new Range(range.to, to)};
        }

        return new Range[]{};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ")";
    }
}