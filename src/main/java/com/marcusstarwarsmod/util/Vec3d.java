package com.marcusstarwarsmod.util;

    public class Vec3d {
        public double x, y, z;

        public Vec3d(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public Vec3d subtract(Vec3d other) {
            return new Vec3d(this.x - other.x, this.y - other.y, this.z - other.z);
        }

        public Vec3d normalize() {
            double length = Math.sqrt(x * x + y * y + z * z);
            return new Vec3d(x / length, y / length, z / length);
        }
    }
