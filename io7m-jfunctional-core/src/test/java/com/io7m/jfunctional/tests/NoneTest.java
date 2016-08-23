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
import com.io7m.jfunctional.PartialProcedureType;
import com.io7m.jfunctional.ProcedureType;
import com.io7m.jfunctional.Some;
import com.io7m.jnull.NullCheckException;
import com.io7m.junreachable.UnreachableCodeException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"boxing", "unchecked", "static-method"})
@EqualityReference
public final class NoneTest
{
  @Test
  public void testEquals()
  {
    Assert.assertEquals(Option.none(), Option.none());

    final OptionType<Integer> s = Option.none();
    Assert.assertEquals(s, s);

    Assert.assertNotEquals(Option.none(), null);
    Assert.assertNotEquals(Option.none(), Option.some("hello"));
  }

  @Test
  public void testEqualsType()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_OK, EqualityValidator.validateClass(
        None.class, AnnotationRequirement.ANNOTATIONS_REQUIRED, true));
  }

  @Test
  public void testIsNone()
  {
    Assert.assertTrue(Option.none().isNone());
    Assert.assertFalse(Option.none().isSome());
  }

  @Test
  public void testNoneAccept_0()
  {
    Assert.assertEquals(
      Option.none().accept(
        new OptionVisitorType<Object, Object>()
        {
          @Override
          public Object none(
            final None<Object> n)
          {
            return TestUtilities.actuallyNull();
          }

          @Override
          public Object some(
            final Some<Object> s)
          {
            throw new UnreachableCodeException();
          }
        }), null);
  }

  @Test
  public void testNoneAccept_1()
    throws Exception
  {
    Assert.assertEquals(
      null, Option.none().acceptPartial(
        new OptionPartialVisitorType<Object, Object, Exception>()
        {
          @Override
          public Object none(
            final None<Object> n)
          {
            return TestUtilities.actuallyNull();
          }

          @Override
          public Object some(
            final Some<Object> s)
          {
            throw new UnreachableCodeException();
          }
        }));
  }

  @Test
  public void testNoneMap_0()
    throws Exception
  {
    Assert.assertEquals(
      Option.none(), Option.none().map(
        new FunctionType<Object, Integer>()
        {
          @Override
          public Integer call(
            final Object x)
          {
            return 23;
          }
        }));
  }

  @Test
  public void testNoneMap_1()
    throws Exception
  {
    Assert.assertEquals(
      Option.none(), Option.none().mapPartial(
        new PartialFunctionType<Object, Integer, Exception>()
        {
          @Override
          public Integer call(
            final Object x)
          {
            return 23;
          }
        }));
  }

  @Test(expected = NullCheckException.class)
  public void testNull_0()
  {
    Option.none().accept(
      (OptionVisitorType<Object, Object>) TestUtilities.actuallyNull());
  }

  @Test(expected = NullCheckException.class)
  public void testNull_2()
    throws Exception
  {
    Option.none().acceptPartial(
      (OptionPartialVisitorType<Object, Object, Exception>) TestUtilities
        .actuallyNull());
  }

  @Test
  public void testToString()
  {
    Assert.assertEquals(Option.none().toString(), Option.none().toString());
  }

  @Test
  public void testNoneMapProcedure_0()
    throws Exception
  {
    final AtomicInteger i = new AtomicInteger(0);
    final OptionType<Integer> none = Option.none();
    none.map_(
      new ProcedureType<Integer>()
      {
        @Override
        public void call(final Integer x)
        {
          i.set(x.intValue());
        }
      });

    Assert.assertEquals(0, i.get());
  }

  @Test
  public void testNoneMapPartialProcedure_0()
    throws Exception
  {
    final AtomicInteger i = new AtomicInteger(0);
    final OptionType<Integer> none = Option.none();
    none.mapPartial_(
      new PartialProcedureType<Integer, IOException>()
      {
        @Override
        public void call(final Integer x)
          throws IOException
        {
          i.set(x.intValue());
        }
      });

    Assert.assertEquals(0, i.get());
  }

  @Test
  public void testNoneMapPartialProcedure_1()
    throws Exception
  {
    final AtomicInteger i = new AtomicInteger(0);
    final OptionType<Integer> none = Option.none();
    none.mapPartial_(
      new PartialProcedureType<Integer, IOException>()
      {
        @Override
        public void call(final Integer x)
          throws IOException
        {
          Assert.fail();
          throw new IOException();
        }
      });
  }

  @Test(expected = NullCheckException.class)
  public void testNoneMapPartialProcedureNull()
    throws Exception
  {
    final AtomicInteger i = new AtomicInteger(0);
    final OptionType<Integer> none = Option.none();
    none.<Exception>mapPartial_(null);
    Assert.fail();
  }

  @Test(expected = NullCheckException.class)
  public void testNoneMapProcedureNull()
    throws Exception
  {
    final AtomicInteger i = new AtomicInteger(0);
    final OptionType<Integer> none = Option.none();
    none.map_(null);
    Assert.fail();
  }
}
