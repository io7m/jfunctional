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

import org.junit.Assert;
import org.junit.Test;

import com.io7m.jfunctional.Failure;
import com.io7m.jfunctional.Success;
import com.io7m.jfunctional.Try;
import com.io7m.jnull.NullCheckException;

public final class TryTest
{
  @SuppressWarnings("static-method") @Test public void testFailure_0()
  {
    final Failure<Integer, ?> s = (Failure<Integer, ?>) Try.failure(23);
    Assert.assertEquals((Integer) 23, s.get());
  }

  @SuppressWarnings("static-method") @Test(
    expected = NullCheckException.class) public void testNull_0()
  {
    Try.success(TestUtilities.actuallyNull());
  }

  @SuppressWarnings("static-method") @Test(
    expected = NullCheckException.class) public void testNull_1()
  {
    Try.failure(TestUtilities.actuallyNull());
  }

  @SuppressWarnings("static-method") @Test public void testSuccess_0()
  {
    final Success<?, Integer> s = (Success<?, Integer>) Try.success(23);
    Assert.assertEquals((Integer) 23, s.get());
  }
}
