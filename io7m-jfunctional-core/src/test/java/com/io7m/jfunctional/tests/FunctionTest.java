/*
 * Copyright © 2014 <code@io7m.com> http://io7m.com
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
import com.io7m.jfunctional.FunctionType;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings({"null", "static-method"})
@EqualityReference
public final class FunctionTest
{
  @Test
  public void testCorrect()
  {
    final FunctionType<Integer, Integer> f =
      new FunctionType<Integer, Integer>()
      {
        @Override
        public Integer call(
          final Integer x)
        {
          return Integer.valueOf(x.intValue() * 3);
        }
      };

    Assert.assertEquals(Integer.valueOf(96), f.call(Integer.valueOf(32)));
  }
}
