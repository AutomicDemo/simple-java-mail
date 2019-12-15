/*
 * Copyright (C) 2009 Benny Bottema (benny@bennybottema.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.simplejavamail.internal.clisupport.valueinterpreters;

import org.bbottema.javareflection.valueconverter.IncompatibleTypeException;
import org.simplejavamail.internal.util.CertificationUtil;

import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class PemFilePathToX509CertificateFunction extends FileBasedFunction<X509Certificate> {
	
	@Override
	public Class<String> getFromType() {
		return String.class;
	}
	
	@Override
	public Class<X509Certificate> getTargetType() {
		return X509Certificate.class;
	}
	
	@NotNull
	@Override
	protected X509Certificate convertFile(File msgFile) {
		try {
			return CertificationUtil.readFromPem(msgFile);
		} catch (CertificateException | NoSuchProviderException | FileNotFoundException e) {
			throw new IncompatibleTypeException(msgFile, String.class, X509Certificate.class, e);
		}
	}
}