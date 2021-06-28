package com.example.wikipill;

import android.app.Activity;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyPresigningClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechPresignRequest;
import com.amazonaws.services.polly.model.Voice;

import java.net.URL;
import java.util.List;

public class TextToSpeechAmazonPolly {

    private static final String COGNITO_POOL_ID = "us-east-1:634afb0a-1c2c-4875-a73d-3f8a93397a85";
    private static final Regions MY_REGION = Regions.US_EAST_1;

   public URL textToSpeech(String text, Activity activity){



// Initialize the Amazon Cognito credentials provider.
       CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
               activity,
               COGNITO_POOL_ID,
               MY_REGION
       );

// Create a client that supports generation of presigned URLs.
       AmazonPollyPresigningClient client = new AmazonPollyPresigningClient(credentialsProvider);

       // Create describe voices request.
       DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

// Synchronously ask Amazon Polly to describe available TTS voices.
       DescribeVoicesResult describeVoicesResult = client.describeVoices(describeVoicesRequest);
       List<Voice> voices = describeVoicesResult.getVoices();

       SynthesizeSpeechPresignRequest synthesizeSpeechPresignRequest =
               new SynthesizeSpeechPresignRequest()
                       // Set the text to synthesize.
                       .withText("Hello world!")
                       // Select voice for synthesis.
                       .withVoiceId(voices.get(0).getId()) // "Joanna"
                       // Set format to MP3.
                       .withOutputFormat(OutputFormat.Mp3);

// Get the presigned URL for synthesized speech audio stream.

        return  client.getPresignedSynthesizeSpeechUrl(synthesizeSpeechPresignRequest);

   }

}
