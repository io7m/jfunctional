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

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

import com.io7m.jfunctional.PartialProcedureType;

public class PartialProcedureTypeTest
{
  @SuppressWarnings("static-method") @Test public void testCorrect()
    throws Throwable
  {
    final AtomicInteger value = new AtomicInteger(0);

    final PartialProcedureType<Integer, Throwable> f =
      new PartialProcedureType<Integer, Throwable>() {
        @Override public void call(
          final Integer x)
        {
          value.addAndGet(x.intValue());
        }
      };

    f.call(Integer.valueOf(32));

    Assert.assertEquals(32, value.intValue());
  }
}
