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

import com.io7m.jequality.annotations.EqualityReference;
import com.io7m.jequality.validator.AnnotationRequirement;
import com.io7m.jequality.validator.EqualityValidator;
import com.io7m.jequality.validator.ValidatorResult;
import com.io7m.jfunctional.FunctionType;
import com.io7m.jfunctional.None;
import com.io7m.jfunctional.Option;
import com.io7m.jfunctional.OptionPartialVisitorType;
import com.io7m.jfunctional.OptionType;
import com.io7m.jfunctional.OptionVisitorType;
import com.io7m.jfunctional.PartialFunctionType;
import com.io7m.jfunctional.Some;
import com.io7m.jnull.NullCheckException;
import com.io7m.junreachable.UnreachableCodeException;

@EqualityReference public final class NoneTest
{
  @SuppressWarnings({ "static-method" }) @Test public void testEquals()
  {
    Assert.assertEquals(Option.none(), Option.none());

    final OptionType<Integer> s = Option.none();
    Assert.assertEquals(s, s);

    Assert.assertNotEquals(Option.none(), null);
    Assert.assertNotEquals(Option.none(), Option.some("hello"));
  }

  @SuppressWarnings("static-method") @Test public void testEqualsType()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        None.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @SuppressWarnings({ "static-method" }) @Test public void testNoneAccept_0()
  {
    Assert.assertEquals(
      Option.none().accept(new OptionVisitorType<Object, Object>() {
        @Override public Object none(
          final None<Object> n)
        {
          return TestUtilities.actuallyNull();
        }

        @Override public Object some(
          final Some<Object> s)
        {
          throw new UnreachableCodeException();
        }
      }),
      null);
  }

  @SuppressWarnings({ "static-method" }) @Test public void testNoneAccept_1()
    throws Exception
  {
    Assert.assertEquals(
      null,
      Option.none().acceptPartial(
        new OptionPartialVisitorType<Object, Object, Exception>() {
          @Override public Object none(
            final None<Object> n)
          {
            return TestUtilities.actuallyNull();
          }

          @Override public Object some(
            final Some<Object> s)
          {
            throw new UnreachableCodeException();
          }
        }));
  }

  @SuppressWarnings({ "static-method", "boxing" }) @Test public
    void
    testNoneMap_0()
      throws Exception
  {
    Assert.assertEquals(
      Option.none(),
      Option.none().map(new FunctionType<Object, Integer>() {
        @Override public Integer call(
          final Object x)
        {
          return 23;
        }
      }));
  }

  @SuppressWarnings({ "static-method", "boxing" }) @Test public
    void
    testNoneMap_1()
      throws Exception
  {
    Assert.assertEquals(
      Option.none(),
      Option.none().mapPartial(
        new PartialFunctionType<Object, Integer, Exception>() {
          @Override public Integer call(
            final Object x)
          {
            return 23;
          }
        }));
  }

  @SuppressWarnings({ "static-method", "unchecked" }) @Test(
    expected = NullCheckException.class) public void testNull_0()
  {
    Option.none().accept(
      (OptionVisitorType<Object, Object>) TestUtilities.actuallyNull());
  }

  @SuppressWarnings({ "static-method", "unchecked" }) @Test(
    expected = NullCheckException.class) public void testNull_2()
    throws Exception
  {
    Option.none().acceptPartial(
      (OptionPartialVisitorType<Object, Object, Exception>) TestUtilities
        .actuallyNull());
  }

  @SuppressWarnings({ "static-method" }) @Test public void testToString()
  {
    Assert.assertEquals(Option.none().toString(), Option.none().toString());
  }
}
