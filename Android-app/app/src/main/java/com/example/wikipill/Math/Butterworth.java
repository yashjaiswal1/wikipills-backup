/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 *  Copyright (c) 2009 by Vinnie Falco
 *  Copyright (c) 2016 by Bernd Porr
 */

package com.example.wikipill.Math;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexUtils;

public class Butterworth extends Cascade {

	class AnalogLowPass extends LayoutBase {

		private int nPoles;

		public AnalogLowPass(int _nPoles) {
			super(_nPoles);
			nPoles = _nPoles;
			setNormal(0, 1);
		}

		public void design() {
			reset();
			double n2 = 2 * nPoles;
			int pairs = nPoles / 2;
			for (int i = 0; i < pairs; ++i) {
				Complex c = ComplexUtils.polar2Complex(1F, Math.PI/2.0
						+ (2 * i + 1) * Math.PI / n2);
				addPoleZeroConjugatePairs(c, Complex.INF);
			}

			if ((nPoles & 1) == 1)
				add(new Complex(-1), Complex.INF);
		}
	}

	private void setupLowPass(int order, double sampleRate,
			double cutoffFrequency, int directFormType) {

		AnalogLowPass m_analogProto = new AnalogLowPass(order);
		m_analogProto.design();

		LayoutBase m_digitalProto = new LayoutBase(order);

		new LowPassTransform(cutoffFrequency / sampleRate, m_digitalProto,
				m_analogProto);

		setLayout(m_digitalProto, directFormType);
	}


	public void lowPass(int order, double sampleRate, double cutoffFrequency) {
		setupLowPass(order, sampleRate, cutoffFrequency,
				DirectFormAbstract.DIRECT_FORM_II);
	}


	public void lowPass(int order, double sampleRate, double cutoffFrequency,
			int directFormType) {
		setupLowPass(order, sampleRate, cutoffFrequency, directFormType);
	}

	
	
	
	private void setupHighPass(int order, double sampleRate,
			double cutoffFrequency, int directFormType) {

		AnalogLowPass m_analogProto = new AnalogLowPass(order);
		m_analogProto.design();

		LayoutBase m_digitalProto = new LayoutBase(order);

		new HighPassTransform(cutoffFrequency / sampleRate, m_digitalProto,
				m_analogProto);

		setLayout(m_digitalProto, directFormType);
	}


	public void highPass(int order, double sampleRate, double cutoffFrequency,
			int directFormType) {
		setupHighPass(order, sampleRate, cutoffFrequency, directFormType);
	}

	public void highPass(int order, double sampleRate, double cutoffFrequency) {
		setupHighPass(order, sampleRate, cutoffFrequency,
				DirectFormAbstract.DIRECT_FORM_II);
	}

	
	
	
	private void setupBandStop(int order, double sampleRate,
			double centerFrequency, double widthFrequency, int directFormType) {

		AnalogLowPass m_analogProto = new AnalogLowPass(order);
		m_analogProto.design();

		LayoutBase m_digitalProto = new LayoutBase(order * 2);

		new BandStopTransform(centerFrequency / sampleRate, widthFrequency
				/ sampleRate, m_digitalProto, m_analogProto);

		setLayout(m_digitalProto, directFormType);
	}

	public void bandStop(int order, double sampleRate, double centerFrequency,
			double widthFrequency) {
		setupBandStop(order, sampleRate, centerFrequency, widthFrequency,
				DirectFormAbstract.DIRECT_FORM_II);
	}


	public void bandStop(int order, double sampleRate, double centerFrequency,
			double widthFrequency, int directFormType) {
		setupBandStop(order, sampleRate, centerFrequency, widthFrequency,
				directFormType);
	}

	
	
	
	private void setupBandPass(int order, double sampleRate,
			double centerFrequency, double widthFrequency, int directFormType) {

		AnalogLowPass m_analogProto = new AnalogLowPass(order);
		m_analogProto.design();

		LayoutBase m_digitalProto = new LayoutBase(order * 2);

		new BandPassTransform(centerFrequency / sampleRate, widthFrequency
				/ sampleRate, m_digitalProto, m_analogProto);

		setLayout(m_digitalProto, directFormType);

	}


	public void bandPass(int order, double sampleRate, double centerFrequency,
			double widthFrequency) {
		setupBandPass(order, sampleRate, centerFrequency, widthFrequency,
				DirectFormAbstract.DIRECT_FORM_II);
	}


	public void bandPass(int order, double sampleRate, double centerFrequency,
			double widthFrequency, int directFormType) {
		setupBandPass(order, sampleRate, centerFrequency, widthFrequency,
				directFormType);
	}

}
