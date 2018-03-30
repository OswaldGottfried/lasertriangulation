package org.name.lasertriangulation.Data;

import org.name.lasertriangulation.Exceptions.FileFormatException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Data {
    private double surfaceRadius;               //радиус фотоприемника LFP
    private double emitterYOffset;              //смещение осветителя Y0
    private double initialEmitterHeightPosition;//Zmin осветителя
    private double emitterHeightStep;           //шаг осветителя
    private double minReflectedLightCoord;
    private double maxReflectedLightCoord;
    private int discretePoints;                 //кол-во точек на сечение NFI
    private int radialProfilesNumber;           //кол-во радиальных сечений
    private double[] xOffset;                   //пси
    private double[] yOffset;                   //тета

    public Data(double surfaceRadius, double emitterXOffset,
                double initialEmitterHeightPosition, double emitterHeightStep,
                double minReflectedLightCoord, double maxReflectedLightCoord,
                int discretePoints, int radialProfilesNumber,
                double[] xOffset, double[] yOffset) {
        this.surfaceRadius = surfaceRadius;
        this.emitterYOffset = emitterXOffset;
        this.initialEmitterHeightPosition = initialEmitterHeightPosition;
        this.emitterHeightStep = emitterHeightStep;
        this.minReflectedLightCoord = minReflectedLightCoord;
        this.maxReflectedLightCoord = maxReflectedLightCoord;
        this.discretePoints = discretePoints;
        this.radialProfilesNumber = radialProfilesNumber;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public double getSurfaceRadius() {
        return surfaceRadius;
    }

    public double getEmitterYOffset() {
        return emitterYOffset;
    }

    public double getInitialEmitterHeightPosition() {
        return initialEmitterHeightPosition;
    }

    public double getEmitterHeightStep() {
        return emitterHeightStep;
    }

    public double getMinReflectedLightCoord() {
        return minReflectedLightCoord;
    }

    public double getMaxReflectedLightCoord() {
        return maxReflectedLightCoord;
    }

    public int getDiscretePoints() {
        return discretePoints;
    }

    public int getRadialProfilesNumber() {
        return radialProfilesNumber;
    }

    public double[] getXOffset() {
        return xOffset;
    }

    public double[] getYOffset() {
        return yOffset;
    }

    public void setSurfaceRadius(double surfaceRadius) {
        this.surfaceRadius = surfaceRadius;
    }

    public void setEmitterYOffset(double emitterYOffset) {
        this.emitterYOffset = emitterYOffset;
    }

    public void setInitialEmitterHeightPosition(double initialEmitterHeightPosition) {
        this.initialEmitterHeightPosition = initialEmitterHeightPosition;
    }

    public void setEmitterHeightStep(double emitterHeightStep) {
        this.emitterHeightStep = emitterHeightStep;
    }

    public void setMinReflectedLightCoord(double minReflectedLightCoord) {
        this.minReflectedLightCoord = minReflectedLightCoord;
    }

    public void setMaxReflectedLightCoord(double maxReflectedLightCoord) {
        this.maxReflectedLightCoord = maxReflectedLightCoord;
    }

    public void setDiscretePoints(int discretePoints) {
        this.discretePoints = discretePoints;
    }

    public void setRadialProfilesNumber(int radialProfilesNumber) {
        this.radialProfilesNumber = radialProfilesNumber;
    }

    public void setxOffset(double[] xOffset) {
        this.xOffset = xOffset;
    }

    public void setyOffset(double[] yOffset) {
        this.yOffset = yOffset;
    }

    private static String readLine(BufferedReader in) throws IOException, Exception {
        String s = in.readLine();
        s = s.trim();
        while (s.indexOf("  ") > 0) s = s.replaceAll("  ", " ");
        return s;
    }

    public Data readData(String fileName) throws IOException, FileFormatException {
        try {
            double surfaceRadius = 0;               //радиус фотоприемника LFP
            double emitterYOffset = 0;              //смещение осветителя
            double initialEmitterHeightPosition = 0;//Zmin осветителя
            double emitterHeightStep = 0;           //шаг осветителя
            double minReflectedLightCoord = 0;
            double maxReflectedLightCoord = 0;
            int discretePoints = 0;                 //количество точек на сечение
            int radialProfilesNumber = 0;           //количество сечений
            double[] xOffset;                       //кси
            double[] yOffset;                       //тета
            int i = 0;

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            String s = readLine(in);
            surfaceRadius = Double.parseDouble(s.substring(0, s.indexOf(" ")));
            emitterYOffset = Double.parseDouble(s.substring(s.indexOf(" ") + 1));

            s = readLine(in);
            initialEmitterHeightPosition = Double.parseDouble(s.substring(0, s.indexOf(" ")));
            emitterHeightStep = Double.parseDouble(s.substring(s.indexOf(" ") + 1));

            s = readLine(in);
            minReflectedLightCoord = Double.parseDouble(s.substring(0, s.indexOf(" ")));
            maxReflectedLightCoord = Double.parseDouble(s.substring(s.indexOf(" ") + 1, s.lastIndexOf(" ")));

            s = readLine(in);
            discretePoints = Integer.parseInt(s.substring(0, s.indexOf(" ")));
            radialProfilesNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1));

            xOffset = new double[discretePoints * radialProfilesNumber];
            yOffset = new double[discretePoints * radialProfilesNumber];
            while (in.ready()) {
                s = readLine(in);
                xOffset[i] = Double.parseDouble(s.substring(s.indexOf(" ") + 1, s.lastIndexOf(" ")));
                yOffset[i++] = Double.parseDouble(s.substring(s.lastIndexOf(" ") + 1));
            }

            return new Data(surfaceRadius, emitterYOffset, initialEmitterHeightPosition, emitterHeightStep, minReflectedLightCoord, maxReflectedLightCoord, discretePoints, radialProfilesNumber, xOffset, yOffset);
        } catch (IOException ex) {
            throw new IOException(ex.toString() + "Файл: " + fileName);
        } catch (Exception ex) {
            throw new FileFormatException(ex.toString() + "Файл: " + fileName);
        }
    }
}

