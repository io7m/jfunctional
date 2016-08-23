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
import com.io7m.jfunctional.Pair;
import com.io7m.jnull.NullCheckException;

@SuppressWarnings({ "boxing", "static-method" }) @EqualityReference public class PairTest
{
  @Test public void testCorrect()
  {
    final Integer i23 = Integer.valueOf(23);
    assert i23 != null;

    final Pair<Integer, String> p = Pair.pair(i23, "hello");
    Assert.assertEquals(Integer.valueOf(23), p.getLeft());
    Assert.assertEquals("hello", p.getRight());
  }

  @Test public void testEquals()
  {
    final Integer i32 = Integer.valueOf(32);
    final Integer i23 = Integer.valueOf(23);
    assert i23 != null;
    assert i32 != null;

    final Pair<Integer, Integer> p0 = Pair.pair(i23, i23);
    final Pair<Integer, Integer> p1 = Pair.pair(i23, i23);
    final Pair<Integer, Integer> p4 = Pair.pair(i32, i23);
    final Pair<Integer, Integer> p5 = Pair.pair(i23, i32);

    Assert.assertEquals(p0, p0);
    Assert.assertNotEquals(p0, null);
    Assert.assertNotEquals(p0, "hello");
    Assert.assertFalse(p4.equals(p5));
    Assert.assertFalse(p5.equals(p4));
    Assert.assertEquals(p1, p0);
    Assert.assertEquals(p0, p1);
  }

  @Test public void testEqualsType()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        Pair.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testHashCode()
  {
    final Integer i23 = Integer.valueOf(23);
    assert i23 != null;

    final Pair<Integer, Integer> p0 = Pair.pair(i23, i23);
    final Pair<Integer, Integer> p1 = Pair.pair(i23, i23);
    Assert.assertEquals(p0.hashCode(), p1.hashCode());
  }

  @Test(expected = NullCheckException.class) public void testNull_0()
  {
    Pair.pair(TestUtilities.actuallyNull(), 23);
  }

  @Test(expected = NullCheckException.class) public void testNull_1()
  {
    Pair.pair(23, TestUtilities.actuallyNull());
  }

  @Test public void testStrings()
  {
    final Integer i23 = Integer.valueOf(23);
    assert i23 != null;
    final Integer i24 = Integer.valueOf(24);
    assert i24 != null;

    final Pair<Integer, Integer> p0 = Pair.pair(i23, i23);
    final Pair<Integer, Integer> p1 = Pair.pair(i24, i23);
    final Pair<Integer, Integer> p2 = Pair.pair(i23, i24);
    final Pair<Integer, Integer> p3 = Pair.pair(i23, i23);

    Assert.assertFalse(p0.toString().equals(p1.toString()));
    Assert.assertFalse(p0.toString().equals(p2.toString()));
    Assert.assertTrue(p0.toString().equals(p0.toString()));
    Assert.assertTrue(p0.toString().equals(p3.toString()));
  }
}
