package de.one1on.sgjscraper.util;

import java.util.concurrent.TimeUnit;

public final class SleepUtils
{
    public static void safeSleep(final TimeUnit timeUnit, final long duration)
    {
        safeSleep(timeUnit.toMillis(duration));
    }

    public static void safeSleep(final long durationInMilliSecs)
    {
        try
        {
            Thread.sleep(durationInMilliSecs);
        }
        catch (final InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    private SleepUtils()
    {
    }
}
