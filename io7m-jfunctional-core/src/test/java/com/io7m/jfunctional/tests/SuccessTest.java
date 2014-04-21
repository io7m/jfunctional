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

import com.io7m.jequality.validator.AnnotationRequirement;
import com.io7m.jequality.validator.EqualityValidator;
import com.io7m.jequality.validator.ValidatorResult;
import com.io7m.jfunctional.Failure;
import com.io7m.jfunctional.Some;
import com.io7m.jfunctional.Success;
import com.io7m.jfunctional.Try;
import com.io7m.jfunctional.TryPartialVisitorType;
import com.io7m.jfunctional.TryType;
import com.io7m.jfunctional.TryVisitorType;
import com.io7m.jnull.NullCheckException;
import com.io7m.junreachable.UnreachableCodeException;

public final class SuccessTest
{
  @SuppressWarnings({ "boxing", "static-method" }) @Test public
    void
    testEquals()
  {
    Assert.assertEquals(Try.success(23), Try.success(23));

    final TryType<Integer, Integer> s = Try.success(23);
    Assert.assertEquals(s, s);

    Assert.assertNotEquals(Try.success(23), Try.success(24));
    Assert.assertNotEquals(Try.success(23), null);
    Assert.assertNotEquals(Try.success(23), Try.success("hello"));
    Assert.assertNotEquals(Try.success(23), Try.failure(23));
  }

  @SuppressWarnings("static-method") @Test public void testEqualsType()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        Some.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @SuppressWarnings("static-method") @Test(
    expected = NullCheckException.class) public void testNull_0()
  {
    Try.success(TestUtilities.actuallyNull());
  }

  @SuppressWarnings({ "static-method", "unchecked" }) @Test(
    expected = NullCheckException.class) public void testNull_1()
  {
    Try
      .success(23)
      .accept(
        (TryVisitorType<Object, Integer, Integer>) TestUtilities
          .actuallyNull());
  }

  @SuppressWarnings({ "static-method", "unchecked" }) @Test(
    expected = NullCheckException.class) public void testNull_2()
    throws Exception
  {
    Try
      .success(23)
      .acceptPartial(
        (TryPartialVisitorType<Object, Integer, Integer, Exception>) TestUtilities
          .actuallyNull());
  }

  @SuppressWarnings({ "static-method", "boxing" }) @Test public
    void
    testSomeAccept_0()
  {
    Assert.assertEquals(
      (Integer) 23,
      Try.success(46).accept(new TryVisitorType<Object, Integer, Integer>() {
        @Override public Integer failure(
          final Failure<Object, Integer> f)
        {
          throw new UnreachableCodeException();
        }

        @Override public Integer success(
          final Success<Object, Integer> s)
        {
          return s.get() / 2;
        }
      }));
  }

  @SuppressWarnings({ "static-method", "boxing" }) @Test public
    void
    testSomeAccept_1()
      throws Exception
  {
    Assert.assertEquals(
      (Integer) 23,
      Try.success(46).acceptPartial(
        new TryPartialVisitorType<Object, Integer, Integer, Exception>() {
          @Override public Integer failure(
            final Failure<Object, Integer> f)
            throws Exception
          {
            throw new UnreachableCodeException();
          }

          @Override public Integer success(
            final Success<Object, Integer> s)
            throws Exception
          {
            return s.get() / 2;
          }
        }));
  }

  @SuppressWarnings({ "static-method", "boxing" }) @Test public
    void
    testSomeGet_0()
  {
    final Success<Object, Integer> s =
      (Success<Object, Integer>) Try.success(23);
    Assert.assertEquals((Integer) 23, s.get());
  }

  @SuppressWarnings({ "boxing", "static-method" }) @Test public
    void
    testToString()
  {
    Assert.assertNotEquals(Try.success(23).toString(), Try
      .success(24)
      .toString());
  }
}
