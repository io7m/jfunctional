/*
 * Copyright © 2016 <code@io7m.com> http://io7m.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jfunctional.tests;

import com.io7m.jequality.annotations.EqualityReference;
import com.io7m.jfunctional.PartialBiFunctionType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Tests for PartialBiFunction.
 */

@SuppressWarnings({"null", "static-method"})
@EqualityReference
public final class PartialBiFunctionTypeTest
{
  @Test
  public void testCorrect()
    throws Exception
  {
    final PartialBiFunctionType<Integer, Double, Integer, Exception> f =
      new PartialBiFunctionType<Integer, Double, Integer, Exception>()
      {
        @Override
        public Integer call(
          final Integer x,
          final Double y)
        {
          return Integer.valueOf((x.intValue() * 3) + y.intValue());
        }
      };

    Assert.assertEquals(
      Integer.valueOf(96 + 23),
      f.call(Integer.valueOf(32), Double.valueOf(23.0)));
  }

  @Test
  public void testRaise()
  {
    try {
      final PartialBiFunctionType<Integer, Double, Integer, IOException> f =
        new PartialBiFunctionType<Integer, Double, Integer, IOException>()
        {
          @Override
          public Integer call(
            final Integer x,
            final Double y)
            throws IOException
          {
            throw new IOException("test");
          }
        };

      f.call(Integer.valueOf(64), Double.valueOf(23.0));
    } catch (final IOException e) {
      Assert.assertEquals("test", e.getMessage());
    }
  }
}
