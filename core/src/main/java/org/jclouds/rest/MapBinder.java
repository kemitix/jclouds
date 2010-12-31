/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.rest;

import java.util.Map;

import org.jclouds.http.HttpRequest;

/**
 * Builds the payload of a Post request.
 * 
 * @author Adrian Cole
 * 
 */
public interface MapBinder extends Binder {

   /**
    * creates and binds the POST payload to the request using parameters specified.
    * 
    * @see org.jclouds.rest.annotations.MapPayloadParam
    */
   <R extends HttpRequest> R bindToRequest(R request, Map<String, String> postParams);
}