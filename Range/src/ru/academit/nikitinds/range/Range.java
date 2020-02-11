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

    public Range getIntersection(Range secondRange) {
        if (secondRange.from > this.to || this.from > secondRange.to) {
            return null;
        } else {
            double maxFrom = Math.max(this.from, secondRange.from);
            double minTo = Math.min(this.to, secondRange.to);
            return new Range(maxFrom, minTo);
        }
    }

    public Range[] getUnion(Range secondRange) {
        Range[] ranges = new Range[2];
        double minFrom = Math.min(this.from, secondRange.from);
        double maxFrom = Math.max(this.from, secondRange.from);
        double minTo = Math.min(this.to, secondRange.to);
        double maxTo = Math.max(this.to, secondRange.to);

        if (secondRange.from > this.to || this.from > secondRange.to) {
            ranges[0] = new Range(minFrom, minTo);
            ranges[1] = new Range(maxFrom, maxTo);
        } else {
            ranges[0] = new Range(minFrom, maxTo);
        }

        return ranges;
    }

    public Range[] getComplement(Range secondRange) {
        Range[] ranges = new Range[2];

        if (secondRange.from > this.to || this.from > secondRange.to) {
            ranges[0] = this;
        } else if (this.from == secondRange.from) {
            if (this.to > secondRange.to) {
                ranges[0] = new Range(secondRange.to, this.to);
            } else {
                return null;
            }
        } else if (this.to == secondRange.to) {
            if (this.from < secondRange.from) {
                ranges[0] = new Range(this.from, secondRange.from);
            } else {
                return null;
            }
        } else {
            if (this.from < secondRange.from) {
                if (this.to > secondRange.to) {
                    ranges[0] = new Range(this.from, secondRange.from);
                    ranges[1] = new Range(secondRange.to, this.to);
                } else {
                    ranges[0] = new Range(this.from, secondRange.from);
                }
            } else {
                if (this.to < secondRange.to) {
                    return null;
                } else {
                    ranges[0] = new Range(secondRange.to, this.to);
                }
            }
        }

        return ranges;
    }
}