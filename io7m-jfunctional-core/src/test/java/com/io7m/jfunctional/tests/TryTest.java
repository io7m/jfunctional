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
import com.io7m.jfunctional.Failure;
import com.io7m.jfunctional.Success;
import com.io7m.jfunctional.Try;
import com.io7m.jnull.NullCheckException;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings({"boxing", "static-method"})
@EqualityReference
public final class TryTest
{
  @Test
  public void testFailure0()
  {
    final Failure<Integer, ?> s = (Failure<Integer, ?>) Try.failure(23);
    Assert.assertEquals((Integer) 23, s.get());
  }

  @Test(expected = NullCheckException.class)
  public void testNull0()
  {
    Try.success(TestUtilities.actuallyNull());
    Assert.fail();
  }

  @Test(expected = NullCheckException.class)
  public void testNull1()
  {
    Try.failure(TestUtilities.actuallyNull());
    Assert.fail();
  }

  @Test
  public void testSuccess0()
  {
    final Success<?, Integer> s = (Success<?, Integer>) Try.success(23);
    Assert.assertEquals((Integer) 23, s.get());
  }
}
