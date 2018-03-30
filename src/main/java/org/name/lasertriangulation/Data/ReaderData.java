package org.name.lasertriangulation.Data;

import org.name.lasertriangulation.Exceptions.FileFormatException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderData {
    private static String readLine(BufferedReader in) throws IOException,Exception
    {
        String s = in.readLine();
        s = s.trim();
        while (s.indexOf("  ")>0)s = s.replaceAll("  "," ");
        return s;
    }
    //Метод для чтения исходных данных из файла fileName
    public Data readData(String fileName) throws IOException,FileFormatException
    {
        try
        {
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
            int i=0;

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            String s=readLine(in);
            surfaceRadius = Double.parseDouble(s.substring(0, s.indexOf(" ")));
            emitterYOffset = Double.parseDouble(s.substring(s.indexOf(" ")+1));

            s=readLine(in);
            initialEmitterHeightPosition = Double.parseDouble(s.substring(0, s.indexOf(" ")));
            emitterHeightStep = Double.parseDouble(s.substring(s.indexOf(" ")+1));

            s=readLine(in);
            minReflectedLightCoord = Double.parseDouble(s.substring(0, s.indexOf(" ")));
            maxReflectedLightCoord = Double.parseDouble(s.substring(s.indexOf(" ")+1, s.lastIndexOf(" ")));

            s=readLine(in);
            discretePoints = Integer.parseInt(s.substring(0, s.indexOf(" ")));
            radialProfilesNumber = Integer.parseInt(s.substring(s.indexOf(" ")+1));

            xOffset=new double[discretePoints*radialProfilesNumber];
            yOffset=new double[discretePoints*radialProfilesNumber];
            while (in.ready())
            {
                s=readLine(in);
                xOffset[i]=Double.parseDouble(s.substring(s.indexOf(" ")+1, s.lastIndexOf(" ")));
                yOffset[i++]=Double.parseDouble(s.substring(s.lastIndexOf(" ")+1));
            }

            return new Data(surfaceRadius, emitterYOffset, initialEmitterHeightPosition, emitterHeightStep, minReflectedLightCoord, maxReflectedLightCoord, discretePoints, radialProfilesNumber, xOffset, yOffset);
        }
        catch (IOException ex)
        {
            throw new IOException(ex.toString()+"Файл: "+fileName);
        }
        catch (Exception ex)
        {
            throw new FileFormatException(ex.toString()+"Файл: "+fileName);
        }
    }
}
