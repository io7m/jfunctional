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

@SuppressWarnings({ "boxing", "unchecked", "static-method" }) @EqualityReference public final class SomeTest
{
  @Test public void testEquals()
  {
    Assert.assertEquals(Option.some(23), Option.some(23));

    final OptionType<Integer> s = Option.some(23);
    Assert.assertEquals(s, s);

    Assert.assertNotEquals(Option.some(23), Option.some(24));
    Assert.assertNotEquals(Option.some(23), null);
    Assert.assertNotEquals(Option.some(23), Option.some("hello"));
    Assert.assertNotEquals(Option.some(23), Option.none());
  }

  @Test public void testEqualsType()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        Some.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testIsSome()
  {
    Assert.assertTrue(Option.some("xyz").isSome());
    Assert.assertFalse(Option.some("xyz").isNone());
  }

  @Test(expected = NullCheckException.class) public void testNull_0()
  {
    Option.some(TestUtilities.actuallyNull());
  }

  @Test(expected = NullCheckException.class) public void testNull_1()
  {
    Option.some(23).accept(
      (OptionVisitorType<Integer, Integer>) TestUtilities.actuallyNull());
  }

  @Test(expected = NullCheckException.class) public void testNull_2()
    throws Exception
  {
    Option.some(23).acceptPartial(
      (OptionPartialVisitorType<Integer, Integer, Exception>) TestUtilities
        .actuallyNull());
  }

  @Test public void testSomeAccept_0()
  {
    Assert.assertEquals(
      (Integer) 23,
      Option.some(46).accept(new OptionVisitorType<Integer, Integer>() {
        @Override public Integer none(
          final None<Integer> n)
        {
          throw new UnreachableCodeException();
        }

        @Override public Integer some(
          final Some<Integer> s)
        {
          return s.get() / 2;
        }
      }));
  }

  @Test public void testSomeAccept_1()
    throws Exception
  {
    Assert.assertEquals(
      (Integer) 23,
      Option.some(46).acceptPartial(
        new OptionPartialVisitorType<Integer, Integer, Exception>() {
          @Override public Integer none(
            final None<Integer> n)
          {
            throw new UnreachableCodeException();
          }

          @Override public Integer some(
            final Some<Integer> s)
          {
            return s.get() / 2;
          }
        }));
  }

  @Test public void testSomeGet_0()
  {
    final Some<Integer> s = (Some<Integer>) Option.some(23);
    Assert.assertEquals((Integer) 23, s.get());
  }

  @Test public void testSomeMap_0()
    throws Exception
  {
    Assert.assertEquals(
      Option.some(23),
      Option.some(46).map(new FunctionType<Integer, Integer>() {
        @Override public Integer call(
          final Integer x)
        {
          return x / 2;
        }
      }));
  }

  @Test public void testSomeMap_1()
    throws Exception
  {
    Assert.assertEquals(
      Option.some(23),
      Option.some(46).mapPartial(
        new PartialFunctionType<Integer, Integer, Exception>() {
          @Override public Integer call(
            final Integer x)
          {
            return x / 2;
          }
        }));
  }

  @Test public void testToString()
  {
    Assert.assertNotEquals(Option.some(23).toString(), Option
      .some(24)
      .toString());
  }
}
