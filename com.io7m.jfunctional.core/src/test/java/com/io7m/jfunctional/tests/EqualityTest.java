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
import com.io7m.jequality.validator.AnnotationRequirement;
import com.io7m.jequality.validator.EqualityValidator;
import com.io7m.jequality.validator.ValidatorResult;
import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Tests for equality.
 */

@EqualityReference
public final class EqualityTest
{
  private static Reflections setupReflections()
  {
    final List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
    classLoadersList.add(ClasspathHelper.contextClassLoader());
    classLoadersList.add(ClasspathHelper.staticClassLoader());

    return new Reflections(
      new ConfigurationBuilder()
        .setScanners(
          new SubTypesScanner(false /* don't exclude Object.class */),
          new ResourcesScanner())
        .setUrls(
          ClasspathHelper.forClassLoader(
            classLoadersList.toArray(new ClassLoader[0])))
        .filterInputsBy(
          new FilterBuilder().include(
            FilterBuilder.prefix("com.io7m.jfunctional"))));
  }

  @Test
  public void testEqualites()
  {
    final Reflections ref = EqualityTest.setupReflections();
    final Set<Class<? extends Object>> types =
      ref.getSubTypesOf(Object.class);

    for (final Class<? extends Object> c : types) {
      assert c != null;

      System.out.printf("Checking %s\n", c.getCanonicalName());
      System.out.flush();

      if (Modifier.isInterface(c.getModifiers())) {
        continue;
      }
      if (c.isAnonymousClass()) {
        continue;
      }

      Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
        .validateClass(c, AnnotationRequirement.ANNOTATIONS_REQUIRED, true));
    }
  }
}
