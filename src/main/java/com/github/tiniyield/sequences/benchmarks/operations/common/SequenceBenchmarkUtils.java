package com.github.tiniyield.sequences.benchmarks.operations.common;

import com.github.tiniyield.sequences.benchmarks.operations.data.providers.number.EvenExceptEndSequenceDataProvider;
import com.github.tiniyield.sequences.benchmarks.operations.data.providers.number.EvenExceptMiddleSequenceDataProvider;
import com.github.tiniyield.sequences.benchmarks.operations.data.providers.number.IntegerDataProvider;
import com.github.tiniyield.sequences.benchmarks.operations.data.providers.object.ValueDataProvider;

public class SequenceBenchmarkUtils {

    private static IntegerDataProvider NUMBERS_DATA_PROVIDER;
    private static ValueDataProvider VALUE_DATA_PROVIDER;
    private static EvenExceptMiddleSequenceDataProvider EVEN_EXCEPT_MIDDLE_DATA_PROVIDER;
    private static EvenExceptEndSequenceDataProvider EVEN_EXCEPT_END_DATA_PROVIDER;

    private static void initEvenExceptEndDataProvider(int size) {
        EVEN_EXCEPT_END_DATA_PROVIDER = new EvenExceptEndSequenceDataProvider(size);
    }

    private static void initEvenExceptMiddleDataProvider(int size) {
        EVEN_EXCEPT_MIDDLE_DATA_PROVIDER = new EvenExceptMiddleSequenceDataProvider(size);
    }

    private static void initValueDataProvider(int size) {
        VALUE_DATA_PROVIDER = new ValueDataProvider(size);
    }

    private static void initNumbersDataProvider(int size) {
        NUMBERS_DATA_PROVIDER = new IntegerDataProvider(size);
    }

    public static IntegerDataProvider getNumbersDataProvider(int size) {
        if(NUMBERS_DATA_PROVIDER == null) {
            initNumbersDataProvider(size);
        }
        return NUMBERS_DATA_PROVIDER;
    }

    public static ValueDataProvider getValueDataProvider(int size) {
        if(VALUE_DATA_PROVIDER == null) {
            initValueDataProvider(size);
        }
        return VALUE_DATA_PROVIDER;
    }

    public static boolean isPrime(Integer value) {
        if (value <= 1) {
            return false;
        }
        if (value <= 3) {
            return true;
        }
        if (value % 2 == 0) {
            return false;
        }
        int i = 3;
        while (i * i <= value) {
            if (value % i == 0) {
                return false;
            }
            i += 2;
        }
        return true;
    }

    public static boolean isEven(Integer value) {
        return value % 2 == 0;
    }

    public static boolean isOdd(Integer value) {
        return value % 2 != 0;
    }

    public static boolean isPositive(Integer value) {
        return value > 0;
    }

    public static Integer increment(Integer value) {
        return value + 1;
    }

    public static EvenExceptMiddleSequenceDataProvider getEvenExceptMiddleDataProvider(int size) {
        if(EVEN_EXCEPT_MIDDLE_DATA_PROVIDER == null) {
            initEvenExceptMiddleDataProvider(size);
        }
        return EVEN_EXCEPT_MIDDLE_DATA_PROVIDER;
    }

    public static EvenExceptEndSequenceDataProvider getEvenExceptEndDataProvider(int size) {
        if(EVEN_EXCEPT_END_DATA_PROVIDER == null) {
            initEvenExceptEndDataProvider(size);
        }
        return EVEN_EXCEPT_END_DATA_PROVIDER;
    }
}
