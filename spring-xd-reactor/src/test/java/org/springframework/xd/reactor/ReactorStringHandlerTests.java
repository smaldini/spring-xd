/*
 * Copyright 2014 the original author or authors.
 *
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
 */
package org.springframework.xd.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link ReactorMessageHandler} by using String types of
 * {@link ReactiveProcessor}. This verifies extracting payload types and
 * wrapping return types in a Message.
 *
 * @author Mark Pollack
 * @author Stephane Maldini
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("reactor.xml")
@DirtiesContext
@ActiveProfiles("string")
public class ReactorStringHandlerTests extends AbstractMessageHandlerTests {

	@Test
	public void striBasedProcessor() throws IOException {
		sendStringMessages();
		for (int i = 0; i < numMessages; i++) {
			Message<?> outputMessage = fromProcessorChannel.receive(2000);
			assertEquals("ping-stringpong", outputMessage.getPayload());
		}
	}
}
